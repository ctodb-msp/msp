import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { AppMenu } from './app-menu.model';
import { AppMenuPopupService } from './app-menu-popup.service';
import { AppMenuService } from './app-menu.service';

@Component({
    selector: 'jhi-app-menu-delete-dialog',
    templateUrl: './app-menu-delete-dialog.component.html'
})
export class AppMenuDeleteDialogComponent {

    appMenu: AppMenu;

    constructor(
        private appMenuService: AppMenuService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.appMenuService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'appMenuListModification',
                content: 'Deleted an appMenu'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-app-menu-delete-popup',
    template: ''
})
export class AppMenuDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private appMenuPopupService: AppMenuPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.appMenuPopupService
                .open(AppMenuDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
