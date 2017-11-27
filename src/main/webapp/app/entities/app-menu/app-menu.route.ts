import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { AppMenuComponent } from './app-menu.component';
import { AppMenuDetailComponent } from './app-menu-detail.component';
import { AppMenuPopupComponent } from './app-menu-dialog.component';
import { AppMenuDeletePopupComponent } from './app-menu-delete-dialog.component';

@Injectable()
export class AppMenuResolvePagingParams implements Resolve<any> {

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

export const appMenuRoute: Routes = [
    {
        path: 'app-menu',
        component: AppMenuComponent,
        resolve: {
            'pagingParams': AppMenuResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'mspApp.appMenu.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'app-menu/:id',
        component: AppMenuDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'mspApp.appMenu.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const appMenuPopupRoute: Routes = [
    {
        path: 'app-menu-new',
        component: AppMenuPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'mspApp.appMenu.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'app-menu/:id/edit',
        component: AppMenuPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'mspApp.appMenu.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'app-menu/:id/delete',
        component: AppMenuDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'mspApp.appMenu.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
