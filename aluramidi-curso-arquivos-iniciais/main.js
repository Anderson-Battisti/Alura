const keysList = document.querySelectorAll( '.tecla' );
 
for ( let i = 0; i < keysList.length; i++ )
{
    const key = keysList[ i ];

    const className = key.classList[ 1 ];

    key.onclick = function ()
    {
        playAudio( "#som_" + className );
    }

    key.onkeydown = function ( event )
    {
        if ( event.code == 'Space' || event.code == 'Enter' )
        {
            key.classList.add( 'ativa' );
        }
    }

    key.onkeyup = function()
    {
        key.classList.remove( 'ativa' );
    }
}
function playAudio( idElementAudio )
{
    document.querySelector( idElementAudio ).play();
}