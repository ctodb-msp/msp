import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { AppApps } from './app-apps.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class AppAppsService {

    private resourceUrl = SERVER_API_URL + 'api/app-apps';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/app-apps';

    constructor(private http: Http) { }

    create(appApps: AppApps): Observable<AppApps> {
        const copy = this.convert(appApps);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(appApps: AppApps): Observable<AppApps> {
        const copy = this.convert(appApps);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<AppApps> {
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
     * Convert a returned JSON object to AppApps.
     */
    private convertItemFromServer(json: any): AppApps {
        const entity: AppApps = Object.assign(new AppApps(), json);
        return entity;
    }

    /**
     * Convert a AppApps to a JSON which can be sent to the server.
     */
    private convert(appApps: AppApps): AppApps {
        const copy: AppApps = Object.assign({}, appApps);
        return copy;
    }
}
