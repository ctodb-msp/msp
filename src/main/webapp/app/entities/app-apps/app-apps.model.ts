import { BaseEntity } from './../../shared';

export const enum AppType {
    'APP',
    'WEDGET'
}

export const enum ReqMode {
    'URL'
}

export const enum AppLevel {
    'SYS',
    'USER'
}

export const enum AppPlatForm {
    'WEB',
    'APP'
}

export class AppApps implements BaseEntity {
    constructor(
        public id?: number,
        public code?: string,
        public name?: string,
        public version?: number,
        public appDesc?: string,
        public appType?: AppType,
        public reqMode?: ReqMode,
        public reqAddr?: string,
        public appLevel?: AppLevel,
        public platform?: AppPlatForm,
        public sort?: number,
        public icon1?: string,
        public icon2?: string,
        public icon3?: string,
        public def1?: string,
        public def2?: string,
        public def3?: string,
        public def4?: string,
        public def5?: string,
        public def6?: string,
        public def7?: string,
        public def8?: string,
        public def9?: string,
        public status?: string,
        public menuId?: number,
        public groupId?: number,
    ) {
    }
}
