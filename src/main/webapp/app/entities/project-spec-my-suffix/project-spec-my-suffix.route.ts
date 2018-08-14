import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ProjectSpecMySuffix } from 'app/shared/model/project-spec-my-suffix.model';
import { ProjectSpecMySuffixService } from './project-spec-my-suffix.service';
import { ProjectSpecMySuffixComponent } from './project-spec-my-suffix.component';
import { ProjectSpecMySuffixDetailComponent } from './project-spec-my-suffix-detail.component';
import { ProjectSpecMySuffixUpdateComponent } from './project-spec-my-suffix-update.component';
import { ProjectSpecMySuffixDeletePopupComponent } from './project-spec-my-suffix-delete-dialog.component';
import { IProjectSpecMySuffix } from 'app/shared/model/project-spec-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class ProjectSpecMySuffixResolve implements Resolve<IProjectSpecMySuffix> {
    constructor(private service: ProjectSpecMySuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((projectSpec: HttpResponse<ProjectSpecMySuffix>) => projectSpec.body));
        }
        return of(new ProjectSpecMySuffix());
    }
}

export const projectSpecRoute: Routes = [
    {
        path: 'project-spec-my-suffix',
        component: ProjectSpecMySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ProjectSpecs'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'project-spec-my-suffix/:id/view',
        component: ProjectSpecMySuffixDetailComponent,
        resolve: {
            projectSpec: ProjectSpecMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ProjectSpecs'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'project-spec-my-suffix/new',
        component: ProjectSpecMySuffixUpdateComponent,
        resolve: {
            projectSpec: ProjectSpecMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ProjectSpecs'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'project-spec-my-suffix/:id/edit',
        component: ProjectSpecMySuffixUpdateComponent,
        resolve: {
            projectSpec: ProjectSpecMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ProjectSpecs'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const projectSpecPopupRoute: Routes = [
    {
        path: 'project-spec-my-suffix/:id/delete',
        component: ProjectSpecMySuffixDeletePopupComponent,
        resolve: {
            projectSpec: ProjectSpecMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ProjectSpecs'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
