import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { AppMenu } from './app-menu.model';
import { AppMenuPopupService } from './app-menu-popup.service';
import { AppMenuService } from './app-menu.service';
import { AppApps, AppAppsService } from '../app-apps';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-app-menu-dialog',
    templateUrl: './app-menu-dialog.component.html'
})
export class AppMenuDialogComponent implements OnInit {

    appMenu: AppMenu;
    isSaving: boolean;

    appapps: AppApps[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private appMenuService: AppMenuService,
        private appAppsService: AppAppsService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.appAppsService.query()
            .subscribe((res: ResponseWrapper) => { this.appapps = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.appMenu.id !== undefined) {
            this.subscribeToSaveResponse(
                this.appMenuService.update(this.appMenu));
        } else {
            this.subscribeToSaveResponse(
                this.appMenuService.create(this.appMenu));
        }
    }

    private subscribeToSaveResponse(result: Observable<AppMenu>) {
        result.subscribe((res: AppMenu) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: AppMenu) {
        this.eventManager.broadcast({ name: 'appMenuListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackAppAppsById(index: number, item: AppApps) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-app-menu-popup',
    template: ''
})
export class AppMenuPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private appMenuPopupService: AppMenuPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.appMenuPopupService
                    .open(AppMenuDialogComponent as Component, params['id']);
            } else {
                this.appMenuPopupService
                    .open(AppMenuDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
