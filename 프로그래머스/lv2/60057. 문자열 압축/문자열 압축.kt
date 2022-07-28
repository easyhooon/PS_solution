class Solution {
    fun solution(s: String): Int {
        var answer = s.length    // 압축하기 전 문자열 길이로 초기화

        //i 개 단위로 자르며 반복
        for (i in 1..s.length / 2) {
            var zipSize = 1 // 압축 크기
            var zipStr = s.substring(0, i) // 압축 문자 패턴
            var curStr = "" // 현재 문자열
            val sb = java.lang.StringBuilder()

            for (start in i..s.length step i) {
                // 비교할 현재 문자열
                // 다음 자를 문자열의 길이가 압축사이즈보다 작으면 남은 글자를 저장
                if (start + i >= s.length) {
                    curStr = s.substring(start, s.length)
                } else {
                    curStr = s.substring(start, start + i)
                }

                // 문자열이 같으면 압축 카운트 증가
                if (curStr == zipStr) {
                    zipSize++
                }
                // 문자열이 다르면 압축된 문자열을 추가하고 타겟 패턴 변경
                else if (zipSize == 1) {
                    sb.append(zipStr)
                    zipStr = curStr
                } else {
                    sb.append(zipSize).append(zipStr)
                    zipStr = curStr
                    zipSize = 1
                }
            }
            //자르고 마지막에 남은 문자열 추가
            if (i != zipStr.length) {
                sb.append(zipStr)
            }

            answer = answer.coerceAtMost(sb.toString().length)
        }

        return answer
    }
}