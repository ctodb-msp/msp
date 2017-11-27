import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { AppGroup } from './app-group.model';
import { AppGroupPopupService } from './app-group-popup.service';
import { AppGroupService } from './app-group.service';

@Component({
    selector: 'jhi-app-group-delete-dialog',
    templateUrl: './app-group-delete-dialog.component.html'
})
export class AppGroupDeleteDialogComponent {

    appGroup: AppGroup;

    constructor(
        private appGroupService: AppGroupService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.appGroupService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'appGroupListModification',
                content: 'Deleted an appGroup'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-app-group-delete-popup',
    template: ''
})
export class AppGroupDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private appGroupPopupService: AppGroupPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.appGroupPopupService
                .open(AppGroupDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
