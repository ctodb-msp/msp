import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { MspAppGroupModule } from './app-group/app-group.module';
import { MspAppAppsModule } from './app-apps/app-apps.module';
import { MspAppMenuModule } from './app-menu/app-menu.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        MspAppGroupModule,
        MspAppAppsModule,
        MspAppMenuModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MspEntityModule {}
