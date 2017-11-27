import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { AppApps } from './app-apps.model';
import { AppAppsPopupService } from './app-apps-popup.service';
import { AppAppsService } from './app-apps.service';
import { AppMenu, AppMenuService } from '../app-menu';
import { AppGroup, AppGroupService } from '../app-group';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-app-apps-dialog',
    templateUrl: './app-apps-dialog.component.html'
})
export class AppAppsDialogComponent implements OnInit {

    appApps: AppApps;
    isSaving: boolean;

    menus: AppMenu[];

    appgroups: AppGroup[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private appAppsService: AppAppsService,
        private appMenuService: AppMenuService,
        private appGroupService: AppGroupService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.appMenuService
            .query({filter: 'appapps-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.appApps.menuId) {
                    this.menus = res.json;
                } else {
                    this.appMenuService
                        .find(this.appApps.menuId)
                        .subscribe((subRes: AppMenu) => {
                            this.menus = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
        this.appGroupService.query()
            .subscribe((res: ResponseWrapper) => { this.appgroups = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.appApps.id !== undefined) {
            this.subscribeToSaveResponse(
                this.appAppsService.update(this.appApps));
        } else {
            this.subscribeToSaveResponse(
                this.appAppsService.create(this.appApps));
        }
    }

    private subscribeToSaveResponse(result: Observable<AppApps>) {
        result.subscribe((res: AppApps) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: AppApps) {
        this.eventManager.broadcast({ name: 'appAppsListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackAppMenuById(index: number, item: AppMenu) {
        return item.id;
    }

    trackAppGroupById(index: number, item: AppGroup) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-app-apps-popup',
    template: ''
})
export class AppAppsPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private appAppsPopupService: AppAppsPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.appAppsPopupService
                    .open(AppAppsDialogComponent as Component, params['id']);
            } else {
                this.appAppsPopupService
                    .open(AppAppsDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
