import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { AppApps } from './app-apps.model';
import { AppAppsService } from './app-apps.service';

@Component({
    selector: 'jhi-app-apps-detail',
    templateUrl: './app-apps-detail.component.html'
})
export class AppAppsDetailComponent implements OnInit, OnDestroy {

    appApps: AppApps;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private appAppsService: AppAppsService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInAppApps();
    }

    load(id) {
        this.appAppsService.find(id).subscribe((appApps) => {
            this.appApps = appApps;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInAppApps() {
        this.eventSubscriber = this.eventManager.subscribe(
            'appAppsListModification',
            (response) => this.load(this.appApps.id)
        );
    }
}
