import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IProjectSpecMySuffix } from 'app/shared/model/project-spec-my-suffix.model';
import { ProjectSpecMySuffixService } from './project-spec-my-suffix.service';

@Component({
    selector: 'jhi-project-spec-my-suffix-update',
    templateUrl: './project-spec-my-suffix-update.component.html'
})
export class ProjectSpecMySuffixUpdateComponent implements OnInit {
    private _projectSpec: IProjectSpecMySuffix;
    isSaving: boolean;

    constructor(private projectSpecService: ProjectSpecMySuffixService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ projectSpec }) => {
            this.projectSpec = projectSpec;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.projectSpec.id !== undefined) {
            this.subscribeToSaveResponse(this.projectSpecService.update(this.projectSpec));
        } else {
            this.subscribeToSaveResponse(this.projectSpecService.create(this.projectSpec));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IProjectSpecMySuffix>>) {
        result.subscribe((res: HttpResponse<IProjectSpecMySuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get projectSpec() {
        return this._projectSpec;
    }

    set projectSpec(projectSpec: IProjectSpecMySuffix) {
        this._projectSpec = projectSpec;
    }
}
