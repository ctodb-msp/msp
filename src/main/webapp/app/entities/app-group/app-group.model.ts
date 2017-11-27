import { BaseEntity } from './../../shared';

export class AppGroup implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
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
        public parentId?: number,
        public children?: BaseEntity[],
    ) {
    }
}
