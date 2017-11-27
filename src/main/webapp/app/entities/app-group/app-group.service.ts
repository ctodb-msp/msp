import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { AppGroup } from './app-group.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class AppGroupService {

    private resourceUrl = SERVER_API_URL + 'api/app-groups';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/app-groups';

    constructor(private http: Http) { }

    create(appGroup: AppGroup): Observable<AppGroup> {
        const copy = this.convert(appGroup);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(appGroup: AppGroup): Observable<AppGroup> {
        const copy = this.convert(appGroup);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<AppGroup> {
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
     * Convert a returned JSON object to AppGroup.
     */
    private convertItemFromServer(json: any): AppGroup {
        const entity: AppGroup = Object.assign(new AppGroup(), json);
        return entity;
    }

    /**
     * Convert a AppGroup to a JSON which can be sent to the server.
     */
    private convert(appGroup: AppGroup): AppGroup {
        const copy: AppGroup = Object.assign({}, appGroup);
        return copy;
    }
}
