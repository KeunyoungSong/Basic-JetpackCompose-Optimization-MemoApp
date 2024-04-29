# Jetpack Compose 최적화 내용 정리

![image](https://developer.android.com/static/develop/ui/compose/images/compose-phases.png)

https://developer.android.com/develop/ui/compose/phases

## `Defer State Reading` case
Issue 1) ContentScreen 에서 스크롤을 하면 layout inspector 의 Recomposition Counts 에서 리컴포지션 횟수가 빠르게 증가하는 상황 확인

- 이슈 1: ContentScreen 의 Title composable 을 보면 외부에서 scroll 값을 전달 받아 Column 의 modifier 에서 사용하고 있다. 오프셋은 offset 100 부터 스크롤이 되는 동안에 스크롤 된 만큼 offset 을 20까지 줄여나간다. 때문에 Title 컴포저블을 매번 그려주고 있다.

- 해결 방안: offset 을 계산하기 위해 외부에서 Int 형의 값을 받아오는게 아닌 람다를 받아와 offset 을 계산하게 로직을 변경한다. 이렇게하면 컴포지션 단계 이후 레이아웃 단계부터 값을 반영해 계산을 한다.

- 변경사항: Title 컴포저블의 Int 타입 scroll 파라미터를 () -> Int 타입으로 변경한다. 변수 이름도 scrollProvider 로 변경함.

- 개선사항: 이전 ContentScreen 스크롤 시 발생하던 여러 번의 리컴포지션을 발생하지 않게 개선하였다. Defer State Reading(상태 읽기 연기) 방식 개선.

## `Using key for skipping recomposition` case
Issue 2) HomeScreen 에서 lazyColumn 을 사용하고 있는데 새로운 memo 생성 시, 이전 모든 메모에서 리컴포지션이 이뤄지는 상황 확인

- 이슈 2: 이전 LazyColum 의 items 에 key 를 지정하지 않아 새로운 아이템이 UI 트리의 시작이나 중간에 추가 시 모든 리스트 요소가 리컴포지션 대상이 되었다.

- 해결 방안: LazyColumn 의 itemsIndexed() 에 key 를 전달하는 것으로 새로운 요소가 UI 트리 시작이나 중간에 추가됐을 때 위치가 이동되어지는 다른 트리 내 요소들이 재사용 가능하도록 한다.

- 변경 사항: LazyColumn 내 각각의 아이템들이  key 값을 갖게되어 새로운 아이템 추가시 나머지 요소의 리컴포지션 스킵이 일어난다.

- 참고: LazyColumn 이 아닌 Column 을 사용하면 아이템 자체에 key 를 줄 수 없지만 Column 내 for 루프 의 Card 를 감싸는 key(id){Card()...} 를 적용하여 유사하게 스킵 효과를 낼 수 있다. 엄밀하게는 LazyColumn 과 Column 에서의 skip 횟수가 차이가 난다.

## `Avoid Backward Writes` case
Issue 3) HomeScreen 에서 Add Memo Button 이 눌릴 때 onClick 과 Button 의 content 에서 각각 count++ 이 호출되는 상황 확인

- 이슈 3: HomeScreen 에서 Add Memo Button 이 눌릴 때 onClick 과 Button 의 content 에서 각각 count++ 이 호출되 지속적으로 count 를 증가시키며 무한루프를 일으킴

- 해결 방안: 한 번 컴포저블이 상태(값)을 읽어오면 컴포저블 내에서 그 값을 변경하지 않게 로직을 변경한다

- 변경 사항: 컴포저블 함수의 onClick(이벤트) 외에 다른 곳에서 상태를 변경하지 않는다. Button content 내 count++ 부분을 제거함
