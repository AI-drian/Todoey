//Empty list, will store to-do's (items)
let items = [];

function addItem() {
    event.preventDefault();
    let userInput = $("#input").val();

    if (userInput.length > 0) {

        let item = {
            text: userInput,
            checked: false
        }

        items.push(item);
        addItemToDB(item);

    } else {
        alert("Nothing to do eh?...");
    }
   
    renderList();
    
}

function renderList() {
    let list = $("ul");
    list.empty();

    for (everyItem of items) {
        list.append(`<li class=Checked{everyItem.checked}> ${everyItem.text} <span class="deleteButton">X</span></li>`);
    }

    DeleteFunction();
    MarkAsDoneFunction();
}

function DeleteFunction() {
    let allDeleteButtons = $(".deleteButton");

    for (let i = 0; i < allDeleteButtons.length; i++) {
        $(allDeleteButtons[i]).click(function () {
            let parentElement = this.parentElement;
            parentElement.style.display = "none";
           deleteItemFromDB(items[i]);
            items.splice(i, 1);
        })
    }
}

function MarkAsDoneFunction() {
    let allItems = $("li");

    for (let i = 0; i < allItems.length; i++) {
        $(allItems[i]).click(function () {
           $(allItems[i]).toggleClass("checked");

            if (items[i].checked === "true") {
                items[i].checked = "false";

            } else if (items[i].checked === "") {
                items[i].checked = "checked";
            }

            console.log(items);
        })
    }
}

async function addItemToDB(item){
let result = await fetch("/rest/Items", {
method: "POST",
body: JSON.stringify(item)
});
}

async function deleteItemFromDB(item){

  let DeleteMe = {
    id: item.id,
    text: item.text,
    checked: item.checked
 }
 
 let result = await fetch("/rest/items/id", {
     method: "DELETE",
     body: JSON.stringify(DeleteMe)
 });
}

async function getItems(){
let result = await fetch('/rest/Items');
items = await result.json();
renderList();
}