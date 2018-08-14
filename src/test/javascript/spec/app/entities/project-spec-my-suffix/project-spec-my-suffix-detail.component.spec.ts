/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { ProjectSpecMySuffixDetailComponent } from 'app/entities/project-spec-my-suffix/project-spec-my-suffix-detail.component';
import { ProjectSpecMySuffix } from 'app/shared/model/project-spec-my-suffix.model';

describe('Component Tests', () => {
    describe('ProjectSpecMySuffix Management Detail Component', () => {
        let comp: ProjectSpecMySuffixDetailComponent;
        let fixture: ComponentFixture<ProjectSpecMySuffixDetailComponent>;
        const route = ({ data: of({ projectSpec: new ProjectSpecMySuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [ProjectSpecMySuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ProjectSpecMySuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ProjectSpecMySuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.projectSpec).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
