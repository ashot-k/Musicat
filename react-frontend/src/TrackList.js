const TrackList = ({tracks}) => {
    return (
        <ol style={{ paddingLeft: 0 }} className="list-group list-group-numbered" type={1} id="trackListing">
            {tracks.map((track) => (
                <li className="fw-semibold list-group-item" style={{ textAlign: 'start', wordWrap: 'anywhere' }}>{track.name}</li>
            ))}
        </ol>
    );
}

export default TrackList;