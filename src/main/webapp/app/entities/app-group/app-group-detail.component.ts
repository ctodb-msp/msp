import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { AppGroup } from './app-group.model';
import { AppGroupService } from './app-group.service';

@Component({
    selector: 'jhi-app-group-detail',
    templateUrl: './app-group-detail.component.html'
})
export class AppGroupDetailComponent implements OnInit, OnDestroy {

    appGroup: AppGroup;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private appGroupService: AppGroupService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInAppGroups();
    }

    load(id) {
        this.appGroupService.find(id).subscribe((appGroup) => {
            this.appGroup = appGroup;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInAppGroups() {
        this.eventSubscriber = this.eventManager.subscribe(
            'appGroupListModification',
            (response) => this.load(this.appGroup.id)
        );
    }
}
