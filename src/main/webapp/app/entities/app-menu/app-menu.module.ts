import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MspSharedModule } from '../../shared';
import {
    AppMenuService,
    AppMenuPopupService,
    AppMenuComponent,
    AppMenuDetailComponent,
    AppMenuDialogComponent,
    AppMenuPopupComponent,
    AppMenuDeletePopupComponent,
    AppMenuDeleteDialogComponent,
    appMenuRoute,
    appMenuPopupRoute,
    AppMenuResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...appMenuRoute,
    ...appMenuPopupRoute,
];

@NgModule({
    imports: [
        MspSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        AppMenuComponent,
        AppMenuDetailComponent,
        AppMenuDialogComponent,
        AppMenuDeleteDialogComponent,
        AppMenuPopupComponent,
        AppMenuDeletePopupComponent,
    ],
    entryComponents: [
        AppMenuComponent,
        AppMenuDialogComponent,
        AppMenuPopupComponent,
        AppMenuDeleteDialogComponent,
        AppMenuDeletePopupComponent,
    ],
    providers: [
        AppMenuService,
        AppMenuPopupService,
        AppMenuResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MspAppMenuModule {}
