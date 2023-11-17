var leftnavbutton = document.getElementById('btnleftnav');
var leftnav = document.getElementById('leftnav');

leftnavbutton.addEventListener("click", changeLeftNavState);

function changeLeftNavState(){
  if (leftnav.classList.contains("left-nav-active")){
    leftnav.classList.remove("left-nav-active");
  } else {
    leftnav.classList.add("left-nav-active");
  }
}
