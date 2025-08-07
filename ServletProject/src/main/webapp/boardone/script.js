function writeSave(){
	if(document.writeForm.write.value==""){  /*null이면*/
		alert("작성자를 입력해 주세요.")
		document.writeForm.write.focus();
		return false;
	}
	
	if(document.writeForm.subject.value==""){  /*null이면*/
		alert("제목을 입력해 주세요.")
		document.writeForm.subject.focus();
		return false;
	}
	
	if(document.writeForm.content.value==""){  /*null이면*/
		alert("내용을 입력해 주세요.")
		document.writeForm.content.focus();
		return false;
	}
	
	if(document.writeForm.pass.value==""){  /*null이면*/
		alert("비밀번호를 입력해 주세요.")
		document.writeForm.pass.focus();
		return false;
	}
	
}