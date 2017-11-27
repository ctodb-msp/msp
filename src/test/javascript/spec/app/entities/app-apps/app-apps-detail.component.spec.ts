/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { MspTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { AppAppsDetailComponent } from '../../../../../../main/webapp/app/entities/app-apps/app-apps-detail.component';
import { AppAppsService } from '../../../../../../main/webapp/app/entities/app-apps/app-apps.service';
import { AppApps } from '../../../../../../main/webapp/app/entities/app-apps/app-apps.model';

describe('Component Tests', () => {

    describe('AppApps Management Detail Component', () => {
        let comp: AppAppsDetailComponent;
        let fixture: ComponentFixture<AppAppsDetailComponent>;
        let service: AppAppsService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [MspTestModule],
                declarations: [AppAppsDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    AppAppsService,
                    JhiEventManager
                ]
            }).overrideTemplate(AppAppsDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(AppAppsDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AppAppsService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new AppApps(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.appApps).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
