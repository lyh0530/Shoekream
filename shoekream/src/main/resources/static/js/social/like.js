

$('span.like').click(function(e){
    e.preventDefault();
});

// const items = document.querySelectorAll(".like");
//     //배열로 저장되기 때문에 forEach로 하나씩 이벤트를 등록해준다.
//     items.forEach((item)=>{
//
//         item.addEventListener('click',()=>{
//
//             const ch=item.childNodes;
//             console.log(ch)
//
//             if(ch[1].getAttribute('src')=='../../img/styleImg/like_icon.png'){
//                 ch[1].setAttribute('src','../../img/styleImg/like_after_icon.png');
//                 // 숫자 ++1
//             }else{
//                 ch[1].setAttribute('src','../../img/styleImg/like_icon.png');
//             }
//
//         })
//     })


function like_clicked(boardIdx, size, lk){
        console.log(boardIdx)

    const items = document.querySelectorAll(".like");
    $('span.like').click(function(e){
        e.preventDefault();
    });
            const ch=lk.childNodes;
            // console.log(lk)
            // console.log(ch)
            // console.log(size)

            if(ch[1].getAttribute('src')=='/img/styleImg/like_icon.png'){
                fetch("http://localhost:8889/api/social/like/" + boardIdx)
                    .then(()=>{
                        ch[1].setAttribute('src','/img/styleImg/like_after_icon.png');
                        ch[2].innerHTML= Number(size) + 1;
                    })
            }else{
                fetch("http://localhost:8889/api/social/unlike/" + boardIdx)
                    .then(()=>{
                        ch[1].setAttribute('src','/img/styleImg/like_icon.png');
                        ch[2].innerHTML= Number(size);
                    })
            }

}

function like_clicked_follow(boardIdx, size, lk){
    console.log(lk)

    const items = document.querySelectorAll(".like");
    $('span.like').click(function(e){
        e.preventDefault();
    });
    const ch=lk.childNodes;
    console.log(lk)
    console.log(ch)
    console.log(size)

    if(ch[1].getAttribute('src')=='/img/styleImg/like_icon.png'){
        fetch("http://localhost:8889/api/social/like/" + boardIdx)
            .then(()=>{
                ch[1].setAttribute('src','/img/styleImg/like_after_icon.png');
                // ch[2].innerHTML= Number(size) + 1;
                document.getElementById('likeid_'+boardIdx).innerHTML = Number(size) + 1;
            })
    }else{
        fetch("http://localhost:8889/api/social/unlike/" + boardIdx)
            .then(()=>{
                ch[1].setAttribute('src','/img/styleImg/like_icon.png');
                // ch[2].innerHTML= Number(size);
                document.getElementById('likeid_'+boardIdx).innerHTML = Number(size);
        })
    }

}