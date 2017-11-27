import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { AppApps } from './app-apps.model';
import { AppAppsPopupService } from './app-apps-popup.service';
import { AppAppsService } from './app-apps.service';

@Component({
    selector: 'jhi-app-apps-delete-dialog',
    templateUrl: './app-apps-delete-dialog.component.html'
})
export class AppAppsDeleteDialogComponent {

    appApps: AppApps;

    constructor(
        private appAppsService: AppAppsService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.appAppsService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'appAppsListModification',
                content: 'Deleted an appApps'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-app-apps-delete-popup',
    template: ''
})
export class AppAppsDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private appAppsPopupService: AppAppsPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.appAppsPopupService
                .open(AppAppsDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
