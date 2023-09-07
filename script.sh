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
url[4]="http://localhost:8080/vaataOsalust/1"
url[5]="http://localhost:8080/newevent"



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

catch response_data status_code curl -ks -w "%{stderr}%{http_code}" -H "Content-type: application/x-www-form-urlencoded"  -d "isikukood=41111111111&isikutyypid=1&eesnimi=Karin&perekonnanimi=Kask&yritusid=1&makseviisid=2"  -X POST   $value
if [ "$status_code" != "302" ]; then
	echo -e "\e[31mError: $status_code -> "$value

else
	echo "Vigu pole, kood: $status_code -> "$value
fi

#POST
value="http://localhost:8080/editeraisikuosalus"

catch response_data status_code curl -ks -w "%{stderr}%{http_code}" -H "Content-type: application/x-www-form-urlencoded"  -d "id=2&isikukood=31111111111&isikutyypid=1&eesnimi=Raul&perekonnanimi=Raju&yritusid=1&makseviisid=2"  -X POST   $value
if [ "$status_code" != "302" ]; then
	echo -e "\e[31mError: $status_code -> "$value

else
	echo "Vigu pole, kood: $status_code -> "$value
fi

#POST
value="http://localhost:8080/editettevotteosalus"

catch response_data status_code curl -ks -w "%{stderr}%{http_code}" -H "Content-type: application/x-www-form-urlencoded"  -d "id=1&registrikood=342c&isikutyypid=2&juriidilinenimi=ABC&yritusid=1&makseviisid=1&tulijatearv=11"  -X POST   $value
if [ "$status_code" != "302" ]; then
	echo -e "\e[31mError: $status_code -> "$value

else
	echo "Vigu pole, kood: $status_code -> "$value
fi


#POST
value="http://localhost:8080/save"

catch response_data status_code curl -ks -w "%{stderr}%{http_code}" -H "Content-type: application/x-www-form-urlencoded"  -d "nimetus=Maamess&aeg=2024-06-26 09:00:00&koht=Tartu"  -X POST   $value
if [ "$status_code" != "302" ]; then
	echo -e "\e[31mError: $status_code -> "$value

else
	echo "Vigu pole, kood: $status_code -> "$value
fi


#DELETE

urldel[1]="http://localhost:8080/deleteOsalus/2"
urldel[2]="http://localhost:8080/deleteYritus/2"

for valdel in "${urldel[@]}"
do
	catch response_data status_code curl -ks -w "%{stderr}%{http_code}" -X DELETE $valdel
	if [ "$status_code" != "302" ]; then
		echo -e "\e[31mError: $status_code -> "$valdel

	else
	echo "Vigu pole, kood: $status_code -> "$valdel
	fi
done













