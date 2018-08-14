import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared';
import {
    ProjectSpecMySuffixComponent,
    ProjectSpecMySuffixDetailComponent,
    ProjectSpecMySuffixUpdateComponent,
    ProjectSpecMySuffixDeletePopupComponent,
    ProjectSpecMySuffixDeleteDialogComponent,
    projectSpecRoute,
    projectSpecPopupRoute
} from './';

const ENTITY_STATES = [...projectSpecRoute, ...projectSpecPopupRoute];

@NgModule({
    imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ProjectSpecMySuffixComponent,
        ProjectSpecMySuffixDetailComponent,
        ProjectSpecMySuffixUpdateComponent,
        ProjectSpecMySuffixDeleteDialogComponent,
        ProjectSpecMySuffixDeletePopupComponent
    ],
    entryComponents: [
        ProjectSpecMySuffixComponent,
        ProjectSpecMySuffixUpdateComponent,
        ProjectSpecMySuffixDeleteDialogComponent,
        ProjectSpecMySuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationProjectSpecMySuffixModule {}
