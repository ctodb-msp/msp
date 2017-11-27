/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { MspTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { AppMenuDetailComponent } from '../../../../../../main/webapp/app/entities/app-menu/app-menu-detail.component';
import { AppMenuService } from '../../../../../../main/webapp/app/entities/app-menu/app-menu.service';
import { AppMenu } from '../../../../../../main/webapp/app/entities/app-menu/app-menu.model';

describe('Component Tests', () => {

    describe('AppMenu Management Detail Component', () => {
        let comp: AppMenuDetailComponent;
        let fixture: ComponentFixture<AppMenuDetailComponent>;
        let service: AppMenuService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [MspTestModule],
                declarations: [AppMenuDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    AppMenuService,
                    JhiEventManager
                ]
            }).overrideTemplate(AppMenuDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(AppMenuDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AppMenuService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new AppMenu(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.appMenu).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
