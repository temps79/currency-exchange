import axios from "axios";

export  default {
    getCurrencyToday(){
        return axios.get(window.restUrl + "/currency/today")
            .then(res => res.data)
            .catch(err => console.error(err));
    }
}