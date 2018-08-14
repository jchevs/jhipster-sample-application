import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IProjectSpecMySuffix } from 'app/shared/model/project-spec-my-suffix.model';

type EntityResponseType = HttpResponse<IProjectSpecMySuffix>;
type EntityArrayResponseType = HttpResponse<IProjectSpecMySuffix[]>;

@Injectable({ providedIn: 'root' })
export class ProjectSpecMySuffixService {
    private resourceUrl = SERVER_API_URL + 'api/project-specs';

    constructor(private http: HttpClient) {}

    create(projectSpec: IProjectSpecMySuffix): Observable<EntityResponseType> {
        return this.http.post<IProjectSpecMySuffix>(this.resourceUrl, projectSpec, { observe: 'response' });
    }

    update(projectSpec: IProjectSpecMySuffix): Observable<EntityResponseType> {
        return this.http.put<IProjectSpecMySuffix>(this.resourceUrl, projectSpec, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IProjectSpecMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IProjectSpecMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
