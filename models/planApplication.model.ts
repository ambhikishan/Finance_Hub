// export interface PlanApplication {
//     planApplicationId?:number;
//     appliedAmount?:number;
//     applicationDate?:string;
//     proofDocument?:string;
//     remarks?:string;
//     status?:string;
//     savingsPlan?:string;
//     // userId?:string;
// }

export interface PlanApplication {
    [x: string]: any;
    planApplicationId?: number,
    appliedAmount?: number,
    status?: string,
    applicationDate?: string,
    remarks?: string,
    proofDocument?: string,
    savingsPlan?:any
}
