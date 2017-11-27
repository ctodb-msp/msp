import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { AppMenu } from './app-menu.model';
import { AppMenuService } from './app-menu.service';

@Component({
    selector: 'jhi-app-menu-detail',
    templateUrl: './app-menu-detail.component.html'
})
export class AppMenuDetailComponent implements OnInit, OnDestroy {

    appMenu: AppMenu;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private appMenuService: AppMenuService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInAppMenus();
    }

    load(id) {
        this.appMenuService.find(id).subscribe((appMenu) => {
            this.appMenu = appMenu;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInAppMenus() {
        this.eventSubscriber = this.eventManager.subscribe(
            'appMenuListModification',
            (response) => this.load(this.appMenu.id)
        );
    }
}
