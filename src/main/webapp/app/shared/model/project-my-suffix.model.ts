export interface IProjectMySuffix {
    id?: number;
    projectName?: string;
    projectId?: string;
}

export class ProjectMySuffix implements IProjectMySuffix {
    constructor(public id?: number, public projectName?: string, public projectId?: string) {}
}
