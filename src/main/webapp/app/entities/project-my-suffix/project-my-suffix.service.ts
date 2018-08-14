import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IProjectMySuffix } from 'app/shared/model/project-my-suffix.model';

type EntityResponseType = HttpResponse<IProjectMySuffix>;
type EntityArrayResponseType = HttpResponse<IProjectMySuffix[]>;

@Injectable({ providedIn: 'root' })
export class ProjectMySuffixService {
    private resourceUrl = SERVER_API_URL + 'api/projects';

    constructor(private http: HttpClient) {}

    create(project: IProjectMySuffix): Observable<EntityResponseType> {
        return this.http.post<IProjectMySuffix>(this.resourceUrl, project, { observe: 'response' });
    }

    update(project: IProjectMySuffix): Observable<EntityResponseType> {
        return this.http.put<IProjectMySuffix>(this.resourceUrl, project, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IProjectMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IProjectMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
