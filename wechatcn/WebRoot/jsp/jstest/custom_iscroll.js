var myScroll;
var pullDownEl;
var pullDownOffset;
var pullUpEl;
var pullUpOffset;

function loaded() {
	pullDownEl = document.getElementById("pullDown");
	pullDownOffset = pullDownEl.offsetHeight;
	pullUpEl = document.getElementById("pullUp");
	pullUpOffset = pullUpEl.offsetHeight;

	myScroll = new iScroll("page_list", {
		hScroll : false,
		vScroll : true,
		useTransition : true,
		useTransform : true,
		hScrollbar : false,
		vScrollbar : false,
		topOffset : pullDownOffset,
		onRefresh : function() {
			if (pullDownEl.className.match("loading")) {
				pullDownEl.className = "";
				pullDownEl.querySelector(".pullDownLabel").innerHTML = "下拉刷新...";
			} else if (pullUpEl.className.match("loading")) {
				pullUpEl.className = "";
				pullUpEl.querySelector(".pullUpLabel").innerHTML = "上拉加载更多";
			}
		},
		onScrollMove : function() {
			if (this.y > 10 && !pullDownEl.className.match("flip")) {
				pullDownEl.className = "flip";
				pullDownEl.querySelector(".pullDownLabel").innerHTML = "松开刷新...";
				this.minScrollY = 0;
			} else if (this.y < 10 && pullDownEl.className.match("flip")) {
				pullDownEl.className = "";
				pullDownEl.querySelector(".pullDownLabel").innerHTML = "下拉刷新...";
				this.minScrollY = -pullDownOffset;
			} else if (this.y < (this.maxScrollY - 20) && !pullUpEl.className.match("flip")) {
				pullUpEl.className = "flip";
				pullUpEl.querySelector(".pullUpLabel").innerHTML = "松开即可加载";
				this.maxScrollY = this.maxScrollY;
			} else if (this.y > (this.maxScrollY - 20) && pullUpEl.className.match("flip")) {
				pullUpEl.className = "";
				pullUpEl.querySelector(".pullUpLabel").innerHTML = "上拉加载更多";
				this.maxScrollY = this.maxScrollY;
			}
		},
		onScrollEnd : function() {
			if (pullDownEl.className.match("flip")) {
				pullDownEl.className = "loading";
				pullDownEl.querySelector(".pullDownLabel").innerHTML = "Loading...";
				pullDownAction();
			} else if (pullUpEl.className.match("flip")) {
				pullUpEl.className = "loading";
				pullUpEl.querySelector(".pullUpLabel").innerHTML = "Loading...";
				pullUpAction();
			}
		}
	});
	
	setTimeout(function() {
		document.getElementById("page_list").style.left = "0";
	}, 800);
}