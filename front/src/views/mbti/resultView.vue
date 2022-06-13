<template>
  <div>
    <div
      v-if="res.E > 0 && res.N < 0 && res.T > 0"
      class="mbti-font"
      style="background-color: #e2b1aa; height: 100vh"
    >
      <div class="container w-50">
        <div class="d-flex justify-content-center">
          <img
            src="@/assets/page1.png"
            alt=""
            style="margin: 0px auto; width: 293.328px; height: 293.219px"
          />
        </div>
        <div style="text-align: center">
          <div>
            <strong>[따로 또 같이 셰어하우스]</strong>
          </div>
          <div>#아늑함 #개성 #안락함 #편안함 #따로또같이 #모여봐요_동물의숲</div>
          <br />
          당신은 실용성과 사회성, 자아실현을 중요시하는 사람이네요! 드라마 [청춘시대] 속 '벨
          에포크'와 같은 셰어하우스가 잘 맞아요. 다 같이 모일 수 있는 거실에서 사람들과 함께
          떠들다가도 나만의 방으로 들어가 상상의 나래를 펼칠 수 있어요. 책상에 앉아 자기 계발을 할
          수도 있겠죠. 공용 공간이든 개인의 방이든 마냥 예쁘기만 한 가구보다는 실용적인 가구를 놓는
          것을 좋아해요.
        </div>
      </div>
    </div>
    <div
      v-if="res.E < 0 && res.N < 0 && res.T > 0"
      class="mbti-font"
      style="background-color: #feedc3; height: 100vh"
    >
      <div class="container w-50">
        <div class="d-flex justify-content-center">
          <img
            src="@/assets/page2.png"
            alt=""
            style="margin: 0px auto; width: 293.328px; height: 293.219px"
          />
        </div>
        <div style="text-align: center">
          <div>
            <strong>[내 맘대로 하우스]</strong>
          </div>
          <div>#ego #취존 #내맘대로살거야_말리지마</div>
          <br />
          당신은 실용성과 독립성, 자아실현을 중요시하는 사람이네요! 애니메이션 [네모바지 스폰지밥]
          속 징징이의 집이 잘 어울려요. 다른 사람의 간섭은 노노! 혼자만의 방에서 취미와 여가생활을
          즐기는 편이에요. 가끔은 ‘나’에 대해 깊은 고민을 하기도 해요. 당신은 집 전체를 나만의
          실용적인 아지트로 꾸미는 것을 좋아해요
        </div>
      </div>
    </div>
    <div
      v-if="res.E > 0 && res.N > 0 && res.T > 0"
      class="mbti-font"
      style="background-color: #bdefe2; height: 100vh"
    >
      <div class="container w-50">
        <div class="d-flex justify-content-center">
          <img
            src="@/assets/page3.png"
            alt=""
            style="margin: 0px auto; width: 293.328px; height: 293.219px"
          />
        </div>
        <div style="text-align: center">
          <div>
            <strong>[오손도손 홈 스윗 홈]</strong>
          </div>
          <div>#왁자지껄 #오손도손 #시끌벅적 #정다운</div>
          <br />
          당신은 실용성과 사회성, 그리고 휴식을 중요시하는 사람이네요! 드라마 [거침없이 하이킥] 속
          대가족이 모여 사는 집처럼 온 가족이 둘러앉아 이야기를 나눌 수 있는 넓은 집이 딱 맞아요.
          가족과 함께 생활하지 않더라도 친구들을 불러서 시끌벅적하게 노는 것을 좋아해요. 가구는
          실용성을 생각해 꼭 필요한 것 위주로 놓는 편이네요. 다른 사람들과 재미있게 놀면서도 휴식은
          꼭 필요하다고 생각하는 편이에요.
        </div>
      </div>
    </div>
    <div
      v-if="res.E < 0 && res.N > 0 && res.T > 0"
      class="mbti-font"
      style="background-color: #e3d5ba; height: 100vh"
    >
      <div class="container w-50">
        <div class="d-flex justify-content-center">
          <img
            src="@/assets/page4.png"
            alt=""
            style="margin: 0px auto; width: 293.328px; height: 293.219px"
          />
        </div>
        <div style="text-align: center">
          <div>
            <strong>[나만의 꿈꾸는 다락방]</strong>
          </div>
          <div>#새벽갬성 #심플라이프 #나혼자산다 #이_선_넘으면_침범이야_삐</div>
          <br />
          당신은 실용성과 독립성, 그리고 휴식을 중요시하는 사람이네요! 애니메이션 [네모바지
          스폰지밥] 속 뚱이의 집이 잘 어울려요. 꼭 필요한 가구나 물품 위주로 집을 꾸미고, 혼자
          시간을 보내는 것을 즐깁니다. 집 안을 북적북적하게 만들기보다는 소박하고 평온한 휴식의
          공간으로 활용하는 편이에요.
        </div>
      </div>
    </div>
    <div
      v-if="res.E > 0 && res.N < 0 && res.T < 0"
      class="mbti-font"
      style="background-color: #49153c; height: 100vh"
    >
      <div class="container w-50">
        <div class="d-flex justify-content-center">
          <img
            src="@/assets/page5.png"
            alt=""
            style="margin: 0px auto; width: 293.328px; height: 293.219px"
          />
        </div>
        <div style="text-align: center; color: white">
          <div>
            <strong>[낭만 가득 파티 하우스]</strong>
          </div>
          <div>#파뤼피플 #낭만_빼면_시체 #나래바 #집이냐_게스트하우스냐</div>
          <br />
          당신은 심미성, 사회성, 자아실현을 중요시하는 사람이네요! 코미디언 박나래 씨의 집처럼
          언제든 친구들을 불러 파티를 즐길 수 있는 집이 잘 어울려요. 보기만 해도 눈이 즐거운 소위
          '예쁜 쓰레기'들을 집안 곳곳에 비치하는 걸 좋아하네요. 다소 적적한 주말에는 사랑하는
          사람들을 불러 요리를 대접해 보는 건 어떨까요? 해가 지면 낭만적인 도시의 풍경을 바라보며
          유쾌한 대화를 나누는 것도 좋을 것 같아요.
        </div>
      </div>
    </div>
    <div
      v-if="res.E < 0 && res.N < 0 && res.T < 0"
      class="mbti-font"
      style="background-color: #e6eafa; height: 100vh"
    >
      <div class="container w-50">
        <div class="d-flex justify-content-center">
          <img
            src="@/assets/page6.png"
            alt=""
            style="margin: 0px auto; width: 293.328px; height: 293.219px"
          />
        </div>
        <div style="text-align: center">
          <div>
            <strong>[무엇이든 가능한 드림 하우스] </strong>
          </div>
          <div>#어드벤처 #꿈과_희망이_가득한 #환상의_나라 #당신의_상상은_현실이_된다</div>
          <br />
          당신은 심미성, 독립성, 자아실현을 중요시하는 사람이네요! 에니메이션 [UP] 속 날아가는 풍선
          집처럼 어린 시절의 꿈을 마음껏 펼칠 수 있는 드림 하우스가 잘 어울려요. 정해진 규칙을
          따르는 획일화된 집보다는 당신의 상상력을 한껏 발휘할 수 있는 집을 좋아하네요. 당신의 미적
          취향에 맞게 아기자기한 드림 하우스를 꾸며 보세요. [UP]의 할아버지처럼 당신의 꿈을 쫓는 건
          어떨까요?
        </div>
      </div>
    </div>
    <div
      v-if="res.E > 0 && res.N > 0 && res.T < 0"
      class="mbti-font"
      style="background-color: #e7ffe7; height: 100vh"
    >
      <div class="container w-50">
        <div class="d-flex justify-content-center">
          <img
            src="@/assets/page7.png"
            alt=""
            style="margin: 0px auto; width: 293.328px; height: 293.219px"
          />
        </div>
        <div style="text-align: center">
          <div>
            <strong>[어디든 떠나요 힐링 하우스]</strong>
          </div>
          <div>#힐링캠프 #효리네민박 #캠핑카 #자연</div>
          <br />
          당신은 심미성, 사회성, 휴식을 중요시하는 사람이네요! 예능 &lt; 효리네 민박 &gt; 속 민박집
          또는 캠핑카처럼 초록빛 가득한 자연 속에서 편안히 쉴 수 있는 집이 잘 어울려요. 살랑살랑
          불어오는 바람을 맞으며 여유롭게 낮잠을 청하는 삶을 꿈꾸네요. 밤에는 모닥불을 피우며 주변
          사람들과 도란도란 담소를 나누는 시간도 낭만적이죠? 당신에게는 지금 자연 속에서의 힐링이
          필요할지도 몰라요.
        </div>
      </div>
    </div>
    <div
      v-if="res.E < 0 && res.N > 0 && res.T < 0"
      class="mbti-font"
      style="background-color: #d4d8ff; height: 100vh"
    >
      <div class="container w-50">
        <div class="d-flex justify-content-center">
          <img
            src="@/assets/page8.png"
            alt=""
            style="margin: 0px auto; width: 293.328px; height: 293.219px"
          />
        </div>
        <div style="text-align: center">
          <div>
            <strong>[혼자라서 좋은 마이 스페이스]</strong>
          </div>
          <div>#몽환 #새벽갬성 #안정 #평화 #B612 #낭만 #고독 #나만의_행성</div>
          <br />
          당신은 심미성, 독립성, 휴식을 중요시하는 사람이네요! 고독을 진정으로 즐길 줄 아는
          사람이고요. 동화 [어린왕자] 속 행성 B612처럼 오롯이 혼자만의 시간을 보낼 수 있는 집이 잘
          어울려요. 당신의 입맛에 따라 구축한 신비로운 분위기의 공간이라면 더욱 좋겠죠? 가만히 앉아
          사색을 즐기거나 당신만의 상상의 나래를 마음껏 펼칠 수 있는 공간을 좋아하네요.
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      res: this.$store.state.mbti,
    };
  },
  created() {
    console.log(this.res);
  },
};
</script>

<style></style>
