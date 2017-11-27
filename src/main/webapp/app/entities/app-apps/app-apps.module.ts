import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MspSharedModule } from '../../shared';
import {
    AppAppsService,
    AppAppsPopupService,
    AppAppsComponent,
    AppAppsDetailComponent,
    AppAppsDialogComponent,
    AppAppsPopupComponent,
    AppAppsDeletePopupComponent,
    AppAppsDeleteDialogComponent,
    appAppsRoute,
    appAppsPopupRoute,
    AppAppsResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...appAppsRoute,
    ...appAppsPopupRoute,
];

@NgModule({
    imports: [
        MspSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        AppAppsComponent,
        AppAppsDetailComponent,
        AppAppsDialogComponent,
        AppAppsDeleteDialogComponent,
        AppAppsPopupComponent,
        AppAppsDeletePopupComponent,
    ],
    entryComponents: [
        AppAppsComponent,
        AppAppsDialogComponent,
        AppAppsPopupComponent,
        AppAppsDeleteDialogComponent,
        AppAppsDeletePopupComponent,
    ],
    providers: [
        AppAppsService,
        AppAppsPopupService,
        AppAppsResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MspAppAppsModule {}
