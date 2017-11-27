/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { MspTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { AppGroupDetailComponent } from '../../../../../../main/webapp/app/entities/app-group/app-group-detail.component';
import { AppGroupService } from '../../../../../../main/webapp/app/entities/app-group/app-group.service';
import { AppGroup } from '../../../../../../main/webapp/app/entities/app-group/app-group.model';

describe('Component Tests', () => {

    describe('AppGroup Management Detail Component', () => {
        let comp: AppGroupDetailComponent;
        let fixture: ComponentFixture<AppGroupDetailComponent>;
        let service: AppGroupService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [MspTestModule],
                declarations: [AppGroupDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    AppGroupService,
                    JhiEventManager
                ]
            }).overrideTemplate(AppGroupDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(AppGroupDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AppGroupService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new AppGroup(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.appGroup).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
