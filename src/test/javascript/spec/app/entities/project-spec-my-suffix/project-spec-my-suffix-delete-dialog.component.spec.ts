/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { ProjectSpecMySuffixDeleteDialogComponent } from 'app/entities/project-spec-my-suffix/project-spec-my-suffix-delete-dialog.component';
import { ProjectSpecMySuffixService } from 'app/entities/project-spec-my-suffix/project-spec-my-suffix.service';

describe('Component Tests', () => {
    describe('ProjectSpecMySuffix Management Delete Component', () => {
        let comp: ProjectSpecMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<ProjectSpecMySuffixDeleteDialogComponent>;
        let service: ProjectSpecMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [ProjectSpecMySuffixDeleteDialogComponent]
            })
                .overrideTemplate(ProjectSpecMySuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ProjectSpecMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProjectSpecMySuffixService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
