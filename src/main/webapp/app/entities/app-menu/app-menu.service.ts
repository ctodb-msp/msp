import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { AppMenu } from './app-menu.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class AppMenuService {

    private resourceUrl = SERVER_API_URL + 'api/app-menus';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/app-menus';

    constructor(private http: Http) { }

    create(appMenu: AppMenu): Observable<AppMenu> {
        const copy = this.convert(appMenu);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(appMenu: AppMenu): Observable<AppMenu> {
        const copy = this.convert(appMenu);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<AppMenu> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    search(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceSearchUrl, options)
            .map((res: any) => this.convertResponse(res));
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        const result = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            result.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return new ResponseWrapper(res.headers, result, res.status);
    }

    /**
     * Convert a returned JSON object to AppMenu.
     */
    private convertItemFromServer(json: any): AppMenu {
        const entity: AppMenu = Object.assign(new AppMenu(), json);
        return entity;
    }

    /**
     * Convert a AppMenu to a JSON which can be sent to the server.
     */
    private convert(appMenu: AppMenu): AppMenu {
        const copy: AppMenu = Object.assign({}, appMenu);
        return copy;
    }
}
