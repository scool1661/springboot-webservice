#!/usr/bin/env bash

# 쉬고 있는 프로파일 찾기
function find_idle_profile()
{
	RESPONSE_CODE=$(curl -s -o /dev/null -w "%{http_code}" http://localhost/profile)
	
	if [ ${RESPONSE_CODE} -ge 400 ]
 	then
 		CURRENT_PROFILE=real2 # 스프링부트 애플리케이션이 실행되고 있지 않은 경우
 	else
 		CURRENT_PROFILE=$(curl -s http://localhost/profile) # real1 또는 real2
 	fi
 	
 	if [ ${CURRENT_PROFILE} == real1 ]
 	then
 		IDLE_PROFILE=real2
 	else
 		IDLE_PROFILE=real1
 	fi
 	
 	echo "${IDLE_PROFILE}"
}

# 쉬고 있는 포트 검색
function find_idle_port()
{
	IDLE_PROFILE=$(find_idle_profile)
	
	if [ ${IDLE_PROFILE} == real1 ]
	then
		echo "8081"
	else
		echo "8082"
	fi
}
