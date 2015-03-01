	function onClickOption(optIdentifier){
		if(optIdentifier == 1)
		{
			resetSideBar();
			handleTopMenuOption1();	
		}
		if(optIdentifier == 2)
		{
			resetSideBar();
			handleTopMenuOption2();	
		}
		if(optIdentifier == 3)
		{
			resetSideBar();
			handleTopMenuOption3();	
		}
		if(optIdentifier == 4)
		{
			resetSideBar();
			handleTopMenuOption4();	
		}
		if(optIdentifier ==5)
		{
			resetSideBar();
			handleTopMenuOption5();	
		}
	}
	function resetSideBar()
	{
		setMyIFrameContent("");
		window.parent.document.getElementById("sidebar").style.display = "";
		window.parent.document.getElementById("sidebarframe").src = "SideBar.html";
	}

	function handleTopMenuOption1()
	{
		setMyIFrameContent("");
		window.parent.document.getElementById("sidebar").style.display = "";
		window.parent.document.getElementById("sidebarframe").src = "RetrieveSideBar.html";
	}
	function handleTopMenuOption2()
	{
		setMyIFrameContent("");
		window.parent.document.getElementById("sidebar").style.display = "";
		window.parent.document.getElementById("sidebarframe").src = "SideBar.html";
	}
	function handleTopMenuOption3()
	{
		setMyIFrameContent("");
		window.parent.document.getElementById("sidebar").style.display = "";
		window.parent.document.getElementById("sidebarframe").src = "SideBar.html";
	}
	function handleTopMenuOption4()
	{
		setMyIFrameContent("");
		window.parent.document.getElementById("sidebar").style.display = "";
		window.parent.document.getElementById("sidebarframe").src = "SideBar.html";
	}
	function handleTopMenuOption5()
	{
		setMyIFrameContent("");
		window.parent.document.getElementById("sidebar").style.display = "";
		window.parent.document.getElementById("sidebarframe").src = "SideBar.html";
	}

