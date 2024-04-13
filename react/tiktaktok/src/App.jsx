import Player from "./components/Player";
import React, {useState} from "react";
import GameBoard from "./components/GameBoard";
import Log from "./components/Log";

function App() {
    const [gameTurns, setGameTurns] = useState([]);
    // 상태 끌어올리기
    const [activePlayer, setActivePlayer] = useState('X');

    function handleSelectSquare() {
        setActivePlayer((currentActivePlayer) => {
            return currentActivePlayer === 'X' ? 'O' : 'X';
        });
        setGameTurns();
    }

    return (
        <main>
            <div id="game-container">
                <ol id="players" className='highlight-player'>
                    <Player
                        playerName='Player 1'
                        playerSymbol='O'
                        isActice = {activePlayer === 'O'}
                    />
                    <Player
                        playerName='Player 2'
                        playerSymbol='X'
                        isActice = {activePlayer === 'X'}
                    />
                </ol>
                <GameBoard
                    onSelectSquare={handleSelectSquare}
                    acticePlayerSymbol={activePlayer}
                />
            </div>

            <Log/>

        < /main>
    )
}

export default App
