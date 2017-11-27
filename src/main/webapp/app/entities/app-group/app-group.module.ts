import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MspSharedModule } from '../../shared';
import {
    AppGroupService,
    AppGroupPopupService,
    AppGroupComponent,
    AppGroupDetailComponent,
    AppGroupDialogComponent,
    AppGroupPopupComponent,
    AppGroupDeletePopupComponent,
    AppGroupDeleteDialogComponent,
    appGroupRoute,
    appGroupPopupRoute,
    AppGroupResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...appGroupRoute,
    ...appGroupPopupRoute,
];

@NgModule({
    imports: [
        MspSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        AppGroupComponent,
        AppGroupDetailComponent,
        AppGroupDialogComponent,
        AppGroupDeleteDialogComponent,
        AppGroupPopupComponent,
        AppGroupDeletePopupComponent,
    ],
    entryComponents: [
        AppGroupComponent,
        AppGroupDialogComponent,
        AppGroupPopupComponent,
        AppGroupDeleteDialogComponent,
        AppGroupDeletePopupComponent,
    ],
    providers: [
        AppGroupService,
        AppGroupPopupService,
        AppGroupResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MspAppGroupModule {}
