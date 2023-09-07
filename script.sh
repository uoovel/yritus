#!/bin/bash
#https://stackoverflow.com/questions/38906626/curl-to-return-http-status-code-along-with-the-response
catch() {
    {
        IFS=$'\n' read -r -d '' "${1}";
        IFS=$'\n' read -r -d '' "${2}";
        (IFS=$'\n' read -r -d '' _ERRNO_; return ${_ERRNO_});
    } < <((printf '\0%s\0%d\0' "$(((({ shift 2; "${@}"; echo "${?}" 1>&3-; } | tr -d '\0' 1>&4-) 4>&2- 2>&1- | tr -d '\0' 1>&4-) 3>&1- | exit "$(cat)") 4>&1-)" "${?}" 1>&2) 2>&1)
}

#GET

url[1]="http://localhost:8080/"
url[2]="http://localhost:8080/koikOsalejad/1"
url[3]="http://localhost:8080/vormOsaleja/1"



for value in "${url[@]}"
do
	catch response_data status_code curl -ks -w "%{stderr}%{http_code}" $value
	if [ "$status_code" != "200" ]; then
		echo -e "\e[31mError: $status_code -> "$value

	else
	echo "Vigu pole, kood: $status_code -> "$value
	fi
done

#POST
value="http://localhost:8080/savecustomer"

catch response_data status_code curl -H "Content-type: application/x-www-form-urlencoded"  -d "isikukood=31111111111"  -X POST  "%{stderr}%{http_code}" $value
if [ "$status_code" != "200" ]; then
	echo -e "\e[31mError: $status_code -> "$value

else
	echo "Vigu pole, kood: $status_code -> "$value
fi














