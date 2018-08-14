/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { ProjectSpecMySuffixUpdateComponent } from 'app/entities/project-spec-my-suffix/project-spec-my-suffix-update.component';
import { ProjectSpecMySuffixService } from 'app/entities/project-spec-my-suffix/project-spec-my-suffix.service';
import { ProjectSpecMySuffix } from 'app/shared/model/project-spec-my-suffix.model';

describe('Component Tests', () => {
    describe('ProjectSpecMySuffix Management Update Component', () => {
        let comp: ProjectSpecMySuffixUpdateComponent;
        let fixture: ComponentFixture<ProjectSpecMySuffixUpdateComponent>;
        let service: ProjectSpecMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [ProjectSpecMySuffixUpdateComponent]
            })
                .overrideTemplate(ProjectSpecMySuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ProjectSpecMySuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProjectSpecMySuffixService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ProjectSpecMySuffix(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.projectSpec = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ProjectSpecMySuffix();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.projectSpec = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
