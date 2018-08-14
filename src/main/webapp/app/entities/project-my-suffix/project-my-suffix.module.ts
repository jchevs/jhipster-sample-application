import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared';
import {
    ProjectMySuffixComponent,
    ProjectMySuffixDetailComponent,
    ProjectMySuffixUpdateComponent,
    ProjectMySuffixDeletePopupComponent,
    ProjectMySuffixDeleteDialogComponent,
    projectRoute,
    projectPopupRoute
} from './';

const ENTITY_STATES = [...projectRoute, ...projectPopupRoute];

@NgModule({
    imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ProjectMySuffixComponent,
        ProjectMySuffixDetailComponent,
        ProjectMySuffixUpdateComponent,
        ProjectMySuffixDeleteDialogComponent,
        ProjectMySuffixDeletePopupComponent
    ],
    entryComponents: [
        ProjectMySuffixComponent,
        ProjectMySuffixUpdateComponent,
        ProjectMySuffixDeleteDialogComponent,
        ProjectMySuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationProjectMySuffixModule {}
