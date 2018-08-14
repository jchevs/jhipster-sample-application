import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProjectSpecMySuffix } from 'app/shared/model/project-spec-my-suffix.model';

@Component({
    selector: 'jhi-project-spec-my-suffix-detail',
    templateUrl: './project-spec-my-suffix-detail.component.html'
})
export class ProjectSpecMySuffixDetailComponent implements OnInit {
    projectSpec: IProjectSpecMySuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ projectSpec }) => {
            this.projectSpec = projectSpec;
        });
    }

    previousState() {
        window.history.back();
    }
}
