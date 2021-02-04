export class Token {
    public expiresIn: number;
    public accessToken: string;

    constructor(expiresIn : number, accessToken : string) {
        this.expiresIn = expiresIn;
        this.accessToken = accessToken;
    }

    getToken() {
        return this.accessToken;
    }


}