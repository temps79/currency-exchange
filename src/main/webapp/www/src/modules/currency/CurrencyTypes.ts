export interface ValuteCursOnDate {
    chCode: string
    code: string
    course: number
    name: string
    nominal: number
}

declare global {
    interface Window {
        restUrl:string
        baseUrl:string
    }
}