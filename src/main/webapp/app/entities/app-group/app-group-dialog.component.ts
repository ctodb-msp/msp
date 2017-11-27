import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { AppGroup } from './app-group.model';
import { AppGroupPopupService } from './app-group-popup.service';
import { AppGroupService } from './app-group.service';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-app-group-dialog',
    templateUrl: './app-group-dialog.component.html'
})
export class AppGroupDialogComponent implements OnInit {

    appGroup: AppGroup;
    isSaving: boolean;

    appgroups: AppGroup[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private appGroupService: AppGroupService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.appGroupService.query()
            .subscribe((res: ResponseWrapper) => { this.appgroups = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.appGroup.id !== undefined) {
            this.subscribeToSaveResponse(
                this.appGroupService.update(this.appGroup));
        } else {
            this.subscribeToSaveResponse(
                this.appGroupService.create(this.appGroup));
        }
    }

    private subscribeToSaveResponse(result: Observable<AppGroup>) {
        result.subscribe((res: AppGroup) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: AppGroup) {
        this.eventManager.broadcast({ name: 'appGroupListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackAppGroupById(index: number, item: AppGroup) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-app-group-popup',
    template: ''
})
export class AppGroupPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private appGroupPopupService: AppGroupPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.appGroupPopupService
                    .open(AppGroupDialogComponent as Component, params['id']);
            } else {
                this.appGroupPopupService
                    .open(AppGroupDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
