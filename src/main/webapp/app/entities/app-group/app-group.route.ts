import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { AppGroupComponent } from './app-group.component';
import { AppGroupDetailComponent } from './app-group-detail.component';
import { AppGroupPopupComponent } from './app-group-dialog.component';
import { AppGroupDeletePopupComponent } from './app-group-delete-dialog.component';

@Injectable()
export class AppGroupResolvePagingParams implements Resolve<any> {

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

export const appGroupRoute: Routes = [
    {
        path: 'app-group',
        component: AppGroupComponent,
        resolve: {
            'pagingParams': AppGroupResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'mspApp.appGroup.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'app-group/:id',
        component: AppGroupDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'mspApp.appGroup.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const appGroupPopupRoute: Routes = [
    {
        path: 'app-group-new',
        component: AppGroupPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'mspApp.appGroup.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'app-group/:id/edit',
        component: AppGroupPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'mspApp.appGroup.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'app-group/:id/delete',
        component: AppGroupDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'mspApp.appGroup.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
