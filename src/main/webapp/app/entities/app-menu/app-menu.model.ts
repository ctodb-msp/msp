import { BaseEntity } from './../../shared';

export const enum ReqMode {
    'URL'
}

export const enum AppLevel {
    'SYS',
    'USER'
}

export class AppMenu implements BaseEntity {
    constructor(
        public id?: number,
        public code?: string,
        public name?: string,
        public reqMode?: ReqMode,
        public reqAddr?: string,
        public appLevel?: AppLevel,
        public sort?: number,
        public icon1?: string,
        public status?: string,
        public appId?: number,
    ) {
    }
}
