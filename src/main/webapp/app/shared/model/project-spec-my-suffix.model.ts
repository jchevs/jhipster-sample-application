export interface IProjectSpecMySuffix {
    id?: number;
    projectId?: string;
    specName?: string;
}

export class ProjectSpecMySuffix implements IProjectSpecMySuffix {
    constructor(public id?: number, public projectId?: string, public specName?: string) {}
}
