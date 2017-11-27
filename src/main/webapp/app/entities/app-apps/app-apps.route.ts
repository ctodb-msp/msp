import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { AppAppsComponent } from './app-apps.component';
import { AppAppsDetailComponent } from './app-apps-detail.component';
import { AppAppsPopupComponent } from './app-apps-dialog.component';
import { AppAppsDeletePopupComponent } from './app-apps-delete-dialog.component';

@Injectable()
export class AppAppsResolvePagingParams implements Resolve<any> {

    constructor(private paginationUtil: JhiPaginationUtil) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const page = route.queryParams['page'] ? route.queryParams['page'] : '1';
        const sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,asc';
        return {
            page: this.paginationUtil.parsePage(page),
            predicate: this.paginationUtil.parsePredicate(sort),
            ascending: this.paginationUtil.parseAscending(sort)
      };
    }
}

export const appAppsRoute: Routes = [
    {
        path: 'app-apps',
        component: AppAppsComponent,
        resolve: {
            'pagingParams': AppAppsResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'mspApp.appApps.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'app-apps/:id',
        component: AppAppsDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'mspApp.appApps.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const appAppsPopupRoute: Routes = [
    {
        path: 'app-apps-new',
        component: AppAppsPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'mspApp.appApps.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'app-apps/:id/edit',
        component: AppAppsPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'mspApp.appApps.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'app-apps/:id/delete',
        component: AppAppsDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'mspApp.appApps.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
