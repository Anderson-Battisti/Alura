const keysList = document.querySelectorAll( '.tecla' );
 
for ( let i = 0; i < keysList.length; i++ )
{
    const key = keysList[ i ];

    const className = key.classList[ 1 ];

    key.onclick = function ()
    {
        playAudio( "#som_" + className );
    }
}
function playAudio( idElementAudio )
{
    document.querySelector( idElementAudio ).play();
}